<?php 

	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	$user = $_REQUEST['username'];
	$pass = $_REQUEST['password'];
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
	$query = "select * from register where username = '".$user."';";
    $q=mysql_query("$query");
	$count=mysql_numrows($q);
    mysql_close($con);

	$f = 0;
	if($count >= 0 )
	{
		while($e=mysql_fetch_assoc($q))
		{
			 $pass_db=$e["password"];
		}	
		if($pass_db == $pass)
			$f = 1;
		else
			$f = 2;
		
	}
	
	$output = array('reply' => $f);
	$resultDataJSON = json_encode($output); 
	print($resultDataJSON); 
?> 