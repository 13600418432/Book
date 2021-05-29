package com.zqx.web;

import com.alibaba.druid.support.opds.udf.ExportSelectListColumns;
import com.google.gson.Gson;
import com.zqx.pojo.User;
import com.zqx.service.UserService;
import com.zqx.service.impl.UserServiceImpl;
import com.zqx.utils.JdbcUtils;
import com.zqx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet{

    UserService userService = new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername  = userService.existsUsername(username);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        User login = userService.login(user);
        if (login == null) {
            req.setAttribute("msg","用户或密码错误！");
            req.setAttribute("username",user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else {
            req.getSession().setAttribute("user",login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code");
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        if (token!=null&&code.equalsIgnoreCase(token)){
            if (userService.existsUsername(user.getUsername())){
                req.setAttribute("msg","用户名已存在！！");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("email",user.getEmail());

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                userService.registerUser(user);
                resp.sendRedirect(req.getContextPath()+"/pages/user/regist_success.jsp");
            }
        }else {
            req.setAttribute("msg","验证码错误！！");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());

            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
