package com.shishir.sg.SGMembers.controllers;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import au.com.bytecode.opencsv.CSVReader;

import com.shishir.sg.SGMembers.model.Member;
import com.shishir.sg.SGMembers.service.EthnicityService;
import com.shishir.sg.SGMembers.service.MemberService;

@Controller
@RequestMapping("/")
public class MainController {
	private Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	MemberService memberService;

	@Autowired
	EthnicityService ethnicityService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap model) {
		return "index.html";
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public @ResponseBody List<Member> listAllMembers(ModelMap model) {
		List<Member> members = memberService.findAllMembers();
		return members;
	}

	@RequestMapping(value = "/UploadData", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/UploadData", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(
			@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				CSVReader membersCSVReader = new CSVReader(
						new InputStreamReader(file.getInputStream()));
				String[] nextLine;
				List<Member> members = new ArrayList<Member>();
				Member member = null;
				while ((nextLine = membersCSVReader.readNext()) != null) {
					member = new Member(Long.valueOf(nextLine[0]), nextLine[1],
							ethnicityService.findById(Integer
									.parseInt(nextLine[2])), Math.round(Float
									.parseFloat(nextLine[3]) / 1000.0),
							Integer.parseInt(nextLine[4]),
							Integer.parseInt(nextLine[5]));

					members.add(member);
				}
				membersCSVReader.close();
				int count = memberService.saveMembers(members);
				return "You successfully uploaded " + count + " records!";
			} catch (Exception e) {
				String message = "You failed to upload " + file.getName()
						+ " => " + e.getMessage();
				logger.error(message, e);
				return message;
			}
		} else {
			return "You failed to upload " + file.getName()
					+ " because the file was empty.";
		}
	}

	@RequestMapping(value = { "/search/", "/search" }, method = RequestMethod.GET)
	public @ResponseBody List<Member> search(
			@RequestParam("query") String searchText) throws Exception {
		List<Member> searchResult = memberService.searchForMember(searchText);
		return searchResult;
	}

	@RequestMapping(value = { "/greeting/" }, method = RequestMethod.GET)
	public @ResponseBody String greeting() {
		Calendar c = Calendar.getInstance();
		int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

		if (timeOfDay >= 0 && timeOfDay < 12) {
			return "Good Morning";
		} else if (timeOfDay >= 12 && timeOfDay < 16) {
			return "Good Afternoon";
		} else if (timeOfDay >= 16 && timeOfDay < 21) {
			return "Good Evening";
		} else if (timeOfDay >= 21 && timeOfDay < 24) {
			return "Good Night";
		}
		return "Good Morning";
	}
}
