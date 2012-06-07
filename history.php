<?php 

$postUrl=$_SERVER["REQUEST_URI"]; 

if(strpos($postUrl,'storeavalue')){ 
  // Storing a Value

} 
else 
{ 
 	$tag = $_POST["tag"];
	$con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");

	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}

    mysql_select_db("db364548356",$con);
	$query = "select * from questions where username = '".$tag."';";
    $q=mysql_query("$query");
	$count=mysql_numrows($q);
    mysql_close($con);

	$falt = "false";
	if($count >= 0 )
	{
		while($e=mysql_fetch_assoc($q))
		{
             $value[]=$e["quest_body"];
			 $value[]=$e["ans_count"];
			 
			 $con = mysql_connect("db2916.perfora.net","dbo364548356","vinkris");

			if (!$con)
			{
				die('Could not connect: ' . mysql_error());
			}

			mysql_select_db("db364548356",$con);
			$queryans = "select * from answers where fq_id = '".$e["q_id"]."';";
			$ans_quest=mysql_query("$queryans");
			$countans=mysql_numrows($ans_quest);
			mysql_close($con);
			
			if($countans >= 0)
			{
				while($f = mysql_fetch_assoc($ans_quest))
				{
					$value[] = $f["answer"];
				}
			}
		}	 
	}
	else
	{
		$value = array($falt,$count);
	}

	$output = array("VALUE", $tag, $value);	
	$resultDataJSON = json_encode($output); 
	echo $resultDataJSON;


} 

?> 