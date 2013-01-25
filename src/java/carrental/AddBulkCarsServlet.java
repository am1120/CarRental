package carrental;

import carrental.helper.CarRentalLogger;
import carrental.helper.DatabaseOperations;

import carrental.model.Car;
import carrental.model.RentalCar;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBulkCarsServlet extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseOperations myDbOps = new DatabaseOperations();
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        //XMLHelper myXMLHelper = new XMLHelper();
        CarRentalLogger carLogger = new CarRentalLogger();
        Random myRandom = new Random();
        String name = request.getParameter("name");
        int numCars = Integer.parseInt(request.getParameter("numcars"));
        String type = request.getParameter("type");

        out.println("<html>");
        out.println("<head><title>AddBulkCarsServlet</title></head><body>");
        out.println("</body></html>");
        
        String queryInsert = "INSERT INTO Cars VALUES(?,?)";
        String queryCommit = "commit";

        if (type.equals("addtojdbc")) {
            carLogger.Logger("Type is addtojdbc, so adding ..");
            myDbOps.insertBulkQuery(name, numCars);
        } else {
            carLogger.Logger("Adding bulk to context ..");
            ServletContext context = this.getServletContext();
            List<Car> carList = (List<Car>) context.getAttribute("Cars");

            // first time we need to initialize the context
            if (carList == null) {
                carList = new ArrayList<Car>();
                context.setAttribute("Cars", carList);
            }
            for (int i = 0; i < numCars; i++) {
                Car aNewCar = new RentalCar(name + "_" + i, Double.parseDouble(Integer.toString(myRandom.nextInt(200000))));
                carList.add(aNewCar); // and the newly created Car
            }


            response.sendRedirect("listcars"); // redirect to list page
        }
        response.sendRedirect("listcarsjdbc"); // redirect to list page
    }
}
