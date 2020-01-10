<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div align="center">
    <form id="registration" name="registration" action="enter" method="post"  style="width:220px">
        <div style="width:250px" align="right">
            <label>Имя
                <input name="name" type="text" id="name" maxlength="20" />
                <br />
            </label>
            <br />
            <label>Login
                <input name="login" type="text" id="login" maxlength="20" />
                <br />
                <br />
            </label>
            <label>Password
                <input name="password" type="text" id="password" maxlength="10" />
                <br />
                <br />
            </label>
            <label>E-mail
                <input name="mail" type="text"  id="mail" maxlength="20" />
                <br />
                <br />
            </label>
            <label>Телефон
                <input name="telephone" type="text"   id="1" maxlength="20" />
                <br />
            </label>
            <p>
                <label>
                    <input type="submit" name="exit" id="exit" value="Выйте"/>
                    <input type="submit" name="enterReg" id="enterID" value="Зарегистрироваться" />
                </label>
            </p>
        </div>
    </form>
</div>
