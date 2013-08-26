function isValid(frm)
{
	var first-name = frm.first-name;
	var last-name = frm.last-name;
	var email-id = frm.email-id;
	if( check(first-name) && check(last-name) && check(email-id) )
	{
		return true;
	}
	else
	{
		return false;
	}
}
function check(cmd)
{
	if(cmd.value == "")
	{
		alert(cmd.name + "  " + "Field Missed");
		cmd.focus();
		return false;
	}
	return true;
}
function isNumber(cmd)
{	
	if(isNaN(cmd.value))
	{
		alert(cmd.name + " " + "should be numeric value");
		cmd.focus();
		return false;
	}
	return true;
}
