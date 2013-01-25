<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>URL Parameter Example</title>
        <link type="text/css" rel="stylesheet" href="/CarRental/css/jdeveloper.css"/>
        <script language="JavaScript" type="text/javascript">
            function generateData() {
                
                // Check Boxes                
                var randomNum1 = Math.floor(Math.random()*2);
                var randomNum2 = Math.floor(Math.random()*2);
                var chkBox1 = new Boolean();
                var chkBox2 = new Boolean();
                
                if (randomNum1 == 1) {
                    chkBox1 = true;
                    document.testURLParams.checkBoxOne.checked = chkBox1;
                }
                else {
                    chkBox1 = false;
                    document.testURLParams.checkBoxOne.checked = chkBox1;
                }
                if (randomNum2 == 1) {
                    chkBox2 = true;
                    document.testURLParams.checkBoxTwo.checked = chkBox2;
                }
                else {
                    chkBox2 = false;
                    document.testURLParams.checkBoxTwo.checked = chkBox2;
                }

                // Text Boxes
                var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
                var stringLength = 10;
                var randomString1 = '';
                var randomString2 = '';
                for (var i=0; i<stringLength; i++) {
                    var rnum = Math.floor(Math.random() * chars.length);
                    randomString1 += chars.substring(rnum,rnum+1);
                }   
                document.testURLParams.stringOne.value = randomString1;
                for (var i=0; i<stringLength; i++) {
                    var rnum = Math.floor(Math.random() * chars.length);
                    randomString2 += chars.substring(rnum,rnum+1);
                }
                document.testURLParams.stringTwo.value = randomString2;
                
                // Combo Boxes
                var select = document.testURLParams.comboBox;
                var items = select.getElementsByTagName('option');
                var index = Math.floor(Math.random() * items.length);
                select.selectedIndex = index;
                
            }
            
            function buildURL() {
                //                window.navigate("result.jsp?chkBox1=" + chkBox1 + "?chkBox2=" + chkBox2 + "?string1=" + randomString1 + "?string2=" + randomString2 + "?comboBox=" + select.selectedIndex);
                //              this.window.location.href="result.jsp?chkBox1=" + chkBox1 + "?chkBox2=" + chkBox2 + "?string1=" + randomString1 + "?string2=" + randomString2 + "?comboBox=" + select.selectedIndex;
                //                return 'result.jsp?chkBox1=' + chkBox1 + '?chkBox2=' + chkBox2 + '?string1=' + randomString1 + '?string2=' + randomString2 + '?comboBox=' + select.selectedIndex;
                //                return "result.jsp";
                //                return "result.jsp";
                document.form.action="result.jsp"
            }
            
            
        </script>
    </head>
    <body onload="javascript:generateData()">
        <h1>Test URL Parameter Collection</h1>
        <h3>Note all values are randomized, just press submit, then back (or refresh) for new values! :-D</h3>
        <form name="testURLParams" method="get" action="result.jsp">
            <p>
                Checkbox one: <input type="checkbox" name="checkBoxOne"/>
            </p>
            <p>
                Checkbox two: <input type="checkbox" name="checkBoxTwo"/>
            </p>
            <p>
                String one: <input type="text" name="stringOne"/>
            </p>
            <p>
                String two: <input type="text" name="stringTwo"/>
            </p>
            <p>
                Combo box: <select name="comboBox">
                    <option value="optionOne">optionOne</option>
                    <option value="optionTwo">optionTwo</option>
                    <option value="optionThree">optionThree</option>
                    <option value="optionFour">optionFour</option>
                    <option value="optionFive">optionFive</option>
                </select>
            </p>
            <p>
                <input type="submit" name="submit" value="submit"/>
            </p>
        </form>
        <h3>
            <a href="/CarRental/home.jsp">Home</a>
        </h3>
    </body>
</html>