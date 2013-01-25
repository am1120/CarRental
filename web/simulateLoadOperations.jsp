<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
        <title>Simulate Application Load</title>
        <link type="text/css" rel="stylesheet" href="css/jdeveloper.css"/>
        <script language="JavaScript" src="modaldbox.js"></script>
        <link type="text/css" rel="stylesheet" href="css/modaldbox.css"/>
        <script language="JavaScript" type="text/javascript">
          function validateForm() {
              if (!(document.simulateLoadOperations.chkBoxInsert.checked && document.simulateLoadOperations.chkBoxUpdate.checked && document.simulateLoadOperations.chkBoxDelete.checked)) {
                  alert("Must choose at least one operation!");
                  return false;
              }
              else {
                  return true;
              }
          }
        </script>
    </head>
    <body>
        <h1>Simulate Load Operations Configuration</h1>
         
        <form name="saveOperations" action="saveoperations" method="post" onsubmit="return validateForm()">
            <p>
                <input type="checkbox" name="chkBoxInsert" value="chkBoxInsert" checked="true" readonly="readonly"/>
                 Perform random inserts
            </p>
            <p>
                <input type="checkbox" name="chkBoxUpdate" value="chkBoxUpdate" checked="true"/>
                 Perform random updates
            </p>
            <p>
                <input type="checkbox" name="chkBoxDelete" value="chkBoxDelete" checked="true"/>
                 Perform random deletes
            </p>
            <p>
                &nbsp;
            </p>
            <h4>
                Note that inserts are mandatory, so the existing rental car fleet is not disturbed.
            </h4>
            <p>
                <input type="submit" name="submit" value="save"/>
            </p>
        </form>
    </body>
</html>