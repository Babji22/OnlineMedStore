
function data(id)
{
    if(id=="Dashboard")
    {
        
        document.getElementById("dash").src="/dashboard";
    }
    else if(id=="buttons2")
    {
        console.log(id);
        document.getElementById("dash").src="/member/findall";
    }

    else if(id=="buttons3")
    {
        console.log(id);
        document.getElementById("dash").src="/drug/alldrugs";
    }

    else if(id=="buttons4")
    {
        console.log(id);
        document.getElementById("dash").src="/order/allorders";
    }

    else if(id=="buttons5")
    {
        console.log(id);
        document.getElementById("dash").src="/AdminDashboard.html";
    }

    else if(id=="buttons6")
    {
        console.log(id);
        document.getElementById("dash").src="/AdminDashboard.html";
    }
}

function orderslistdata()
{
	document.getElementById("dash").src="/order/allorders";
}

function deletedata(id)
{
	window.location.href='/member/delete/'+id;
}

function datas()
{  
        document.getElementById("dash").src='/member/findall';
}

function medicinedata()
{  
        document.getElementById("dash").src='/drug/alldrugs';
}

function updatemedicinedata(id)
{
	document.getElementById("dash").src='/drug/getdrugbyid/'+id;
}


function updatedata(id,val)
{  
	if(val==0)
		{
			const ad=window.location.href='/member/getmember/'+id+'/'+val;
			ad.onload=function admincheck(){
						document.getElementById("name").setAttribute('readonly',true);
						document.getElementById("email").setAttribute('readonly',true);
						document.getElementById("password").setAttribute('readonly',true);
						document.getElementById("phonenumber").setAttribute('readonly',true);
						document.getElementById("date").setAttribute('readonly',true);
						document.getElementById("street").setAttribute('readonly',true);
						document.getElementById("city").setAttribute('readonly',true);
						document.getElementById("state").setAttribute('readonly',true);
						document.getElementById("country").setAttribute('readonly',true);
						document.getElementById("pincode").setAttribute('readonly',true);			
						}
		}   
}


function updatemedicinedata(drugid)
{
	window.location.href='/drug/getdrug/'+drugid;
}

function modifydrug(adminid,drugdata)
{
	window.location.href='/drug/editdrug/'+adminid+'/'+drugdata;
}



function deletedrugdata(drugid)
{
	window.location.href='/drug/deletedrug/'+drugid;
}

function drugdata()
{
	window.location.href='/drug/newdrug';
}

function memberdata(id)
{  
        document.getElementById("dash").src='/member/dashboard/'+id;
}

function memberfun(id,val)
{
        console.log(id);
        document.getElementById("dash").src='/member/getmember/'+id+'/'+val;
	
    

    }
	
	function sent(value)
	{
		if(value=="Admin")
			{
				document.getElementsByTagName("form").action='/register';
				document.getElementsByTagName("form").method='POST';
			}
			else
			{
				document.getElementsByTagName("form").action='/member/register';
			}
			
	}
	
	function errocheck(value)
	{
		if(value.length>=8 )
			{}
			
	}
	
	
	//function message()
	//{
		//cartfun(memberid,0)
	//}
	function cartfun(id,val)
	{
	    if(val==0)
		{
			document.getElementById("deals").src='/member/cartdata/'+id+'/'+val;		    	
		}
		else
		{
			document.getElementById("dash").src='/member/cartdata/'+id+'/'+val;		    
		} 
	}
		
		function cartmethod(drugid,memberid)
			{
			    //if(id=="Dashboard")
			    //{
			        
			    //    document.getElementById("dash").src="/AdminDashboard.html";
			    //}
			    //else 
				
			        console.log(id);
			        document.getElementById("deals").src='/member/cartdata/'+drugid+'/'+memberid;
			    

			    }
		
		function orderfun(id,val)
			{
			    //if(id=="Dashboard")
			    //{
			        
			    //    document.getElementById("dash").src="/AdminDashboard.html";
			    //}
			    //else 
				
				if(val==0)
						{
							document.getElementById("deals").src='/member/orderdata/'+id+'/'+val;		    	
						}
						else
						{
							document.getElementById("dash").src='/member/orderdata/'+id+'/'+val;		    
						}
			    

			    }
				
				
				function logincheck(val,email,password)
				{
					if(val=="member")
						{
							window.location.href='/member/login/'+email+'/'+password;
						}
					
				}
				

	
function memberordersearch(memberid)
{
	window.location.href='/order/memberordersearch/'+memberid;
}			


/*var num=1;
function dec(quantity,price,id)
{
    if(num>1 && num<=quantity)
    {
        num--;
        document.getElementById(id).value=num;
        document.getElementById(id+id).textContent=price*(num);
    }
}

function inc(quantity,price,id)
{
    if(num>=1 && num<quantity)
    {
        num++;
        document.getElementById(id).value=num;
        document.getElementById(id+id).textContent=price*num;
    }
}

let orderedlist=[];
function orderlist(value)
{
	/*for(let i=0;i<cartlist.length;i++)
		{*/
			/*orderedlist.push([3]);
			orderedlist[orderedlist.length-1].push(value);
			
			document.getElementById('dec').disabled=false;
			document.getElementById('inc').disabled=false;
}

var total=1;
function changevalue(value,id)
{
	for(let i=0;i<orderedlist.length;i++)
	{
		if(orderedlist[i][0]==id)
			{
				orderedlist[i][1]=value;
			}
		/*total=7;total+orderedlist[i][3];*
	}
	/*document.getElementById('totalpromo').value=total;*/
/*}


function totalvalue(price,id)
{
	for(let i=0;i<orderedlist.length;i++)
	{
		if(orderedlist[i]==id)
			{
				orderedlist[i][2]=price;
			}
		total=7;/*total+orderedlist[i][3];*
	}
	document.getElementById('totalpromo').value=total;
}*/
/*function totalvalue()
{
	for(let i=0;i<orderedlist.length;i++)
		{
			total=total+orderedlist[i][2];
		}
}*/

function accesskey(id)
{
    
    var divtag=document.createElement("div");
    // console.log(divtag);
    document.body.appendChild(divtag);
    divtag.style.height="40px"
    divtag.style.width="150px"
    // divtag.style.backgroundColor="red";
    divtag.style.position="absolute";
    divtag.style.marginTop="150px";
    divtag.required;
    divtag.style.transition="all 0.5s"
    // divtag.style.border="none";
    // divtag.style.boxShadow="2px 2px 0px grey";
    
    
    var inputtag=document.createElement("input");
    divtag.appendChild(inputtag);
    inputtag.style.height="100%";
    inputtag.style.width="100%";
    inputtag.style.transition="all 0.5s"
    inputtag.style.fontSize="17.5px";
    

    inputtag.placeholder="Access key";
	inputtag.name="accesskey";
    // inputtag.style.backgroundColor="yellow";
    // inputtag.style.border="none";
    // inputtag.style.border="#9b59b6";
    inputtag.body.style.placeholder="Enter Your Access key";
    console.log(inputtag.value);
    if(id="dot-4")
    {
        inputtag.required;
        inputtag.validationMessage="Please fill out this field"
    }
    else if(id="dot-5")
    {
        inputtag.disabled;
    }
    
}

//function check(id)
//{
	//if(id!=null)
		//{
			//document.getElementById("n").innerText(id);
			//document.getElementById("pro").innerHTML(id);
						
		//}
	
//}




