<?php 

	$user = $_REQUEST['username'];
	$frd = $_REQUEST['friend'];
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	
	$f = 0;
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("db364548356",$con);
	
	$qq = mysql_query("select * from register where username = '$frd';");
	$count = mysql_numrows($qq);
	if($count == 1)
		$q=mysql_query("INSERT INTO  `friends` (  `friend_id` ,  `username` ,  `friend_name` ,  `groupid` ) VALUES ('',  '$user',  '$frd', NULL);");
	
	$output = array('reply' => $q);
	$resultDataJSON = json_encode($output); 
	echo $resultDataJSON;

?> 