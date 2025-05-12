// src/main/java/pkg1/Service/student/AttendanceService.java
package pkg1.Service.student;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import pkg1.Entity.student.Attendance;
import pkg1.Entity.student.AttendanceRepo;

@Service("studentAttendanceService")
public class AttendanceService {
    private final AttendanceRepo repo;

    public AttendanceService(AttendanceRepo repo) {
        this.repo = repo;
    }

    /** Save/mark a new attendance record */
    public Attendance markAttendance(Attendance attendance) {
        return repo.save(attendance);
    }

    /** Raw list of attendance records for a student */
    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    /**
     * Returns List of Maps: { "subject": String, "attendance": Integer },
     * matching exactly what your JS expects.
     */
    public List<Map<String, Object>> getAttendancePercentageByStudentId(Long studentId) {
        List<Attendance> records = repo.findByStudentId(studentId);

        // group by subject
        Map<String, List<Attendance>> bySubject = records.stream()
            .collect(Collectors.groupingBy(Attendance::getSubject));

        List<Map<String,Object>> result = new ArrayList<>();
        bySubject.forEach((subject, recs) -> {
            long total   = recs.size();
            long present = recs.stream()
                               .filter(r -> "Present".equalsIgnoreCase(r.getStatus()))
                               .count();
            int pct = total > 0
                   ? (int) Math.round(present * 100.0 / total)
                   : 0;

            Map<String,Object> m = new HashMap<>();
            m.put("subject", subject);
            m.put("attendance", pct);
            result.add(m);
        });

        return result;
    }
}
