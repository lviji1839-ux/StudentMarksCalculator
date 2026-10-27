MEC-1: Student Marks and Grade Calculator using Java Servlet
===========================================================

Requirements:
- JDK 17
- Eclipse IDE with Maven support
- Apache Tomcat 9
- Internet connection on first Maven build (to download dependencies)

Project files:
- src/main/java/com/student/MarksServlet.java : validates marks and calculates total, average, grade
- src/main/webapp/index.html                  : input form
- src/main/webapp/result.jsp                  : result page
- pom.xml                                     : Maven WAR configuration

Import into Eclipse:
1. Extract StudentMarksCalculator.zip.
2. Open Eclipse.
3. Choose File > Import.
4. Select Maven > Existing Maven Projects, then Next.
5. Browse to the extracted StudentMarksCalculator folder.
6. Ensure pom.xml is selected, then click Finish.
7. Wait for Maven dependencies to download.
8. If needed, right-click project > Maven > Update Project.
9. Configure Tomcat 9 in Eclipse: Window > Show View > Servers, then create a new server.
10. Right-click project > Run As > Run on Server, choose Tomcat 9, then Finish.

Open:
http://localhost:8080/StudentMarksCalculator/

Grade rules:
Average >= 90       A
Average >= 75       B
Average >= 60       C
Average >= 40       D
Average < 40        Fail

Note:
This project uses javax.servlet, which is compatible with Tomcat 9. Use Tomcat 9 rather than Tomcat 10 for this version.
