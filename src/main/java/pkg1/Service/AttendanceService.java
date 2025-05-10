package pkg1.Service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg1.Entity.Attendance;
import pkg1.Entity.Student;
import pkg1.Entity.AttendanceRepo;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;  // ✅ Corrected

    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepo.save(attendance);
    }

    public List<Attendance> getAttendanceByStudentId(Long id) {
        Student student = new Student();
        student.setId(id);
        return attendanceRepo.findByStudent(student);  // ✅ Corrected
    }

    public List<Map<String, Object>> getAttendancePercentageByStudentId(Long studentId) {
        Student student = new Student();
        student.setId(studentId);

        List<Attendance> records = attendanceRepo.findByStudent(student);  // ✅ Corrected

        Map<String, List<Attendance>> grouped = records.stream()
            .collect(Collectors.groupingBy(Attendance::getSubject));

        List<Map<String, Object>> result = new ArrayList<>();

        for (Map.Entry<String, List<Attendance>> entry : grouped.entrySet()) {
            String subject = entry.getKey();
            List<Attendance> subjectRecords = entry.getValue();

            long total = subjectRecords.size();
            long present = subjectRecords.stream()
                .filter(a -> a.getStatus().equalsIgnoreCase("present"))
                .count();

            double percentage = total > 0 ? (present * 100.0) / total : 0.0;

            Map<String, Object> item = new HashMap<>();
            item.put("subject", subject);
            item.put("attendance", Math.round(percentage));

            result.add(item);
        }

        return result;
    }
}
