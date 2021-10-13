package com.example.lab6excercise.usecases;
import com.example.lab6excercise.models.Assignment;
import com.example.lab6excercise.models.Course;
import com.example.lab6excercise.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.lab6excercise.ThymeleafConfig.CSV_TEMPLATE_ENGINE;
@Component
public class StudentsCsvUseCase {
    private final TemplateEngine templateEngine;
    @Autowired
    public StudentsCsvUseCase(@Qualifier(CSV_TEMPLATE_ENGINE) TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }
    public List<Student> getStudentsList() {
        Student student1 = new Student();
        student1.setLastName("Smotherman");
        student1.setFirstName("Kent");

        Student student2 = new Student();
        student2.setLastName("Rogers");
        student2.setFirstName("Norville");

        Course calculusKent = new Course();
        calculusKent.setName("Calculus");

        Course calculusNorville = new Course();
        calculusNorville.setName("Calculus");

        Course advancedJavaKent = new Course();
        advancedJavaKent.setName("Advanced Java");

        Course java101Norville = new Course();
        java101Norville.setName("Java 101");

        Assignment areaUnderTheCurveKent = new Assignment();
        areaUnderTheCurveKent.setName("Area under the curve");
        areaUnderTheCurveKent.setAssignmentDate(LocalDate.parse("2021-09-02"));
        areaUnderTheCurveKent.setGrade(4);

        Assignment areaUnderTheCurveNorville = new Assignment();
        areaUnderTheCurveNorville.setName("Area under the curve");
        areaUnderTheCurveNorville.setAssignmentDate(LocalDate.parse("2021-09-02"));
        areaUnderTheCurveNorville.setGrade(2.8);

        Assignment accelerationKent = new Assignment();
        accelerationKent.setName("Acceleration");
        accelerationKent.setAssignmentDate(LocalDate.parse("2021-09-04"));
        accelerationKent.setGrade(3.9);

        Assignment accelerationNorville = new Assignment();
        accelerationNorville.setName("Acceleration");
        accelerationNorville.setAssignmentDate(LocalDate.parse("2021-09-04"));
        accelerationNorville.setGrade(3.6);

        Assignment dualAxisEquationsKent = new Assignment();
        dualAxisEquationsKent.setName("Dual-axis Equations");
        dualAxisEquationsKent.setAssignmentDate(LocalDate.parse("2021-09-06"));
        dualAxisEquationsKent.setGrade(3.6);

        Assignment dualAxisEquationsNorville = new Assignment();
        dualAxisEquationsNorville.setName("Dual-axis Equations");
        dualAxisEquationsNorville.setAssignmentDate(LocalDate.parse("2021-09-06"));
        dualAxisEquationsNorville.setGrade(3.2);

        Assignment algorithms1Kent = new Assignment();
        algorithms1Kent.setName("Algorithms");
        algorithms1Kent.setAssignmentDate(LocalDate.parse("2021-09-03"));
        algorithms1Kent.setGrade(3.2);

        Assignment algorithms2Kent = new Assignment();
        algorithms2Kent.setName("Algorithms");
        algorithms2Kent.setAssignmentDate(LocalDate.parse("2021-09-05"));
        algorithms2Kent.setGrade(3.7);

        Assignment syntaxNorville = new Assignment();
        syntaxNorville.setName("Syntax");
        syntaxNorville.setAssignmentDate(LocalDate.parse("2021-09-03"));
        syntaxNorville.setGrade(3.5);

        Assignment controlStructuresNorville = new Assignment();
        controlStructuresNorville.setName("Control Structures");
        controlStructuresNorville.setAssignmentDate(LocalDate.parse("2021-05-03"));
        controlStructuresNorville.setGrade(3.1);

        calculusKent.setAssignment(List.of(areaUnderTheCurveKent, accelerationKent, dualAxisEquationsKent));
        calculusNorville.setAssignment(List.of(areaUnderTheCurveNorville, accelerationNorville, dualAxisEquationsNorville));

        advancedJavaKent.setAssignment(List.of(algorithms1Kent, algorithms2Kent));

        java101Norville.setAssignment(List.of(syntaxNorville, controlStructuresNorville));

        student1.setCourse(List.of(calculusKent, advancedJavaKent));
        student2.setCourse(List.of(calculusNorville, java101Norville));
        List<Student> students = List.of(student1, student2);
        return students;
    }

    public String getStudentsAsCsv() {
        List<Student> students = getStudentsList();

        StringWriter writer = new StringWriter();
        Context context = new Context();
        context.setVariable("students", students);
        templateEngine.process("students", context, writer);
        return writer.toString();
    }

    public String getCourseASCsv() {
        List<Student> students = getStudentsList();
        Map<String, List<Student>> courseMap = new HashMap<>();
        for (Student iteratestudents : students){
            List<Course> courseList = iteratestudents.getCourse();
            for (int i=0; i<courseList.size(); i++){
                List<Student> mapStudents;
                if(courseMap.containsKey(courseList.get(i).getName())){
                    mapStudents = courseMap.get(courseList.get(i).getName());
                }else{
                    mapStudents = new ArrayList<>();
                }
                mapStudents.add(iteratestudents);
                courseMap.put(courseList.get(i).getName(), mapStudents);
            }
        }

        StringWriter writer = new StringWriter();
        Context context = new Context();
        context.setVariable("courseMap", courseMap);
        templateEngine.process("courses", context, writer);
        return writer.toString();

    }
}