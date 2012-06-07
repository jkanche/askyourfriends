<?php 

$postUrl=$_SERVER["REQUEST_URI"]; 

if(strpos($postUrl,'storeavalue')){ 
  // Storing a Value
  
	$tag = $_POST["tag"];
	$value = $_POST["value"];
	
	list($ans,$user) = split(',',$value);
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
    $q=mysql_query("INSERT INTO answers VALUES ('$tag','$ans','$user')");
	
    mysql_close($con);
	
} 
else 
{ 
 

} 

?> 