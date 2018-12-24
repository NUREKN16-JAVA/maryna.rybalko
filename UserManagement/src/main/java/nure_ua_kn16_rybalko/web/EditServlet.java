package nure_ua_kn16_rybalko.web;

import nure_ua_kn16_rybalko.User;
import nure_ua_kn16_rybalko.db.DaoFactory;
import nure_ua_kn16_rybalko.db.DatabaseException;
import nure_ua_kn16_rybalko.web.exceptions.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = -2174999686644907833L;
    private static final String EDIT_PAGE = "/edit.jsp";
    private static final String BROWSE_SERVLET = "/browse";
    private static final String ATTR_ERROR = "error";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("okButton") != null) {
            doOk(req, resp);
        } else if (req.getParameter("cancelButton") != null) {
            doCancel(req, resp);
        } else {
            showPage(req, resp);
        }
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(EDIT_PAGE).forward(req, resp);
    }

    private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(BROWSE_SERVLET).forward(req, resp);
    }

    private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = getUserFromRequest(req);
        } catch (ValidationException e) {
            req.setAttribute(ATTR_ERROR, e.getMessage());
            showPage(req, resp);
            return;
        }
        try {
            processUser(user);
        } catch (DatabaseException e) {
            throw new ServletException("ERROR! Could not update users", e);
        }
        req.getRequestDispatcher(BROWSE_SERVLET).forward(req, resp);
    }

    protected void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDAO().update(user);
    }

    public User getUserFromRequest(HttpServletRequest req) throws ValidationException {
        User user = new User();
        String idString = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateOfBirthString = req.getParameter("dateOfBirth");

        if (firstName == null) {
            throw new ValidationException("ERROR! First name is empty!");
        }

        if (lastName == null) {
            throw new ValidationException("ERROR! Last name is empty!");
        }

        if (dateOfBirthString == null) {
            throw new ValidationException("ERROR! Date of birth is empty!");
        }

        if (idString != null) {
            user.setId(new Long(idString));
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);

        try {
            user.setDateOfBirth(DateFormat.getDateInstance().parse(dateOfBirthString));
        } catch (ParseException e) {
            throw new ValidationException("ERROR! Incorrect date format!!", e);
        }

        return user;
    }
}
