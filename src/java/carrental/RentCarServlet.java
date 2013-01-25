package carrental;

import carrental.model.Car;

import carrental.helper.CarStatHelper;

import carrental.model.RentalCar;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class RentCarServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // create some initial cars and populate the Servlet Context
        List<Car> carsList = new ArrayList<Car>();
        /*    carsList.add(new RentalCar("2006", "Jeep Wrangler", 1000.00));
        carsList.add(new RentalCar("2004", "Honda Accord", 23000.00));
        carsList.add(new RentalCar("2005", "Honda Civic", 14000.00));
        carsList.add(new RentalCar("2002", "Nissan Altima", 35000.00));
        carsList.add(new RentalCar("2000", "Ford F150", 100000.00));
        carsList.add(new RentalCar("1990", "Mercury Cougar", 145000.00)); */

        ServletContext globalData = this.getServletContext();
        //globalData.setAttribute("Cars", carsList);

    }

    // let the doPost method handle both GET and POST requests

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // hand off processing to doGet
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        // get the car list attribute  from ServletContext

        ServletContext context = this.getServletContext();
        List<Car> carList = (List<Car>)context.getAttribute("Cars");

        out.println("<html>");
        out.println("<head><title>RentCar</title></head>");
        out.println("<body>");

        try {
            // build a form to rent more cars
            this.buildHTMLForm(out, carList);
            // retrieve car rental parameters

            String selection = request.getParameter("name");
            String miles = request.getParameter("miles");

            // check to see if there is a valid selection and fee
            // to do: need more error checking code for parameters

            if (selection != null && !selection.equals("") && miles != null && !miles.equals("")) {
                CarStatHelper.updateMiles(selection, miles, carList);
            }

            //  provide quick links to call other services of the web application
            //
        } catch (Exception e) {
            out.println("<p>Exception: " + e + "</p>");
            out.println("<p>No cars found!</p>");
        }

        out.println("<p><a href=\"home.jsp\"> Home </a></p>");
        out.println("<p><a href=\"listcars\"> Show rental cars </a></p>");
        out.println("<p><a href=\"addCar.jsp\"> Add a new Rental Car </a></p>");
        out.println("</body>");
        out.println("</html>");
        out.close();

    }

    public void buildHTMLForm(PrintWriter out, List carList) {


        Iterator iter = carList.iterator();
        if (iter.hasNext()) {
            // this builds a form that posts back to itself
            out.println("<form method=\"post\" action=\"rentcar\">");
            // create a html select tag that has each car as an option
            out.println("<select size=\"1\" name=\"id\">");
            // build selection option html with an iterator
            while (iter.hasNext()) {
                String carString = iter.next().toString();
                out.println("<option value=\"" + carString + "\">" + carString);
                out.println("</option>\"");
            }
            out.println("</select>");
            // add a text box to enter the rental fee and a submit button
            out.println("Enter New Miles");
            out.println("<input type=\"text\" name=\"miles\"/>");
            out.println("<input type=\"submit\"  value=\"submit\"/>");
            out.println("</form>"); // end html form tag
        }

    }

}

