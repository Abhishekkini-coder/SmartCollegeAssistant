package pkg1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pkg1.Entity.Student;
import pkg1.Service.PerformanceService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/student")
    public Map<String, Double> getPerformance(@RequestBody Student student) {
        Map<String, Double> performance = new HashMap<>();
        performance.put("averageGrade", performanceService.getAverageGrade(student));
        performance.put("attendancePercentage", performanceService.getAttendancePercentage(student));
        performance.put("taskCompletionRate", performanceService.getTaskCompletionRate(student));
        return performance;
    }
}
