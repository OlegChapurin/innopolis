<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div align="center">
    <form id="enterForm" name="enterForm" method="post" action="enter" style="width:240px">
        <div style="width:240px" align="right">
            <label>Login
                <input type="text" name="login" id="login" maxlength="20" />
                <br />
                <br />
            </label>
            <label>Password
                <input type="text" name="password" id="password"  maxlength="10"/>
            </label>
            <p>
                <label>
                    <input type="submit" name="reg" id="regID" value="Регистрация" />

                    <input type="submit" name="enter" id="enterID" value="Войти" />
                </label>
            </p>
        </div>
    </form>
</div>