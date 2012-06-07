<?php 

	$user = $_REQUEST['username'];
	$grpname = $_REQUEST['grp_name'];
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	 mysql_select_db("db364548356",$con);
	 
	$query = "INSERT INTO `groups` ( `group_id` , `group_name` , `username` ) VALUES ('', '$grpname', '$user');";
	$q=mysql_query("$query");
	
	$query = "select * from groups where username = '".$user."' AND group_name = '".$grpname."';";
	$qs=mysql_query("$query");
	
	while($e=mysql_fetch_assoc($qs))
	{
             $value[] = $e;
	}
	
	$resultDataJSON = json_encode($value); 
	echo $resultDataJSON;

?> 