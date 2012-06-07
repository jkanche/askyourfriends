<?php 

$postUrl=$_SERVER["REQUEST_URI"]; 

if(strpos($postUrl,'storeavalue')){ 
  // Storing a Value

	$tag = $_POST["tag"];
	$value = $_POST["value"];
	
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
    $q=mysql_query("INSERT INTO questions VALUES ('$tag','$value')");
	
    mysql_close($con);

} 
else 
{ 
  // Retrieving a Value

	$tag = $_POST["tag"];
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");

	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
	$query = "select * from register where username = '".$tag."';";
    $q=mysql_query("$query");
	$count=mysql_numrows($q);
    mysql_close($con);

	$f = "false";
	if($count >= 0 )
	{
		$value = array($f,$count);
	}
	else
	{
		while($e=mysql_fetch_assoc($q))
             $value[]=$e["username"];
	}

	$output = array("VALUE", $tag, $value);	
	$resultDataJSON = json_encode($output); 
	echo $resultDataJSON;

} 

?> 