package com.example.coronavirustracker.Controller;

import com.example.coronavirustracker.Model.LocationStats;
import com.example.coronavirustracker.Service.CoronaServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaServiceData coronaServiceData;

    @GetMapping("/")
    public String home(Model model){
        final List<LocationStats> allStats = coronaServiceData.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int latestTotalCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("latestTotalCases",latestTotalCases);
        return "home";
    }
}
