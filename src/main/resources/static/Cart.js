
function dec(quantity,price,id)
{
    for(let i=0;i<orderedlist.length;i++)
	{
		if(orderedlist[i][0]==id)
		{
            var num=orderedlist[i][1];
            break;
        }
    }
    if(num>1 && num<=quantity)
    {
        num--;
        document.getElementsByName(id)[0].value=num;
        changevalue(Number(num),id);
        document.getElementsByName(id)[1].textContent=price*(num);
        totalvalue(Number(price*(num)),id);
    }
}

function inc(quantity,price,id)
{
    for(let i=0;i<orderedlist.length;i++)
	{
		if(orderedlist[i][0]==id)
		{
            var num=orderedlist[i][1];
            break;
        }
    }
    if(num>=1 && num<quantity)
    {

        num++;
        document.getElementsByName(id)[0].value=num;
        changevalue(Number(num),Number(id));
        document.getElementsByName(id)[1].textContent=price*(num);
        totalvalue(Number(price*(num)),id);
    }
}


let orderedlist=[];
/*console.log(orderedlist);*/
function orderlist(v,id)
{
    
        if(document.getElementById(id).checked)
        {

			const temporderedlist=[];
            temporderedlist.push(Number(v));
            temporderedlist.push(Number(document.getElementsByName(v)[0].value));
            temporderedlist.push(Number(document.getElementsByName(v)[1].innerHTML));
            orderedlist.push(temporderedlist);

			
            /*console.log(id);*/
			const data=document.getElementsByClassName(v);
            /*console.log(data);*/
            for(let i=0;i<data.length;i++)
            {
                data[i].disabled=false;
            }
			
            /*console.log(orderedlist);*/
        }
        else
        {
        /*console.log("jj");*/
            for(let i=0;i<orderedlist.length;i++)
            {
                console.log(v);
                if(orderedlist[i][0]==v)
                {
                    orderedlist.splice(i,1);
                    break;
                }
            }
            const data=document.getElementsByClassName(v);
            /*console.log(data);*/
            for(let i=0;i<data.length;i++)
            {
                data[i].disabled=true;
            }
            /*console.log(orderedlist);*/
            
        }   
        updatetotal();

        
}


function changevalue(value,id)
{
    /*console.log(orderedlist);*/
    
    
	for(let i=0;i<orderedlist.length;i++)
	{
        
       
		if(orderedlist[i][0]==id)
			{
                
                    orderedlist[i][1]=Number(value);
                    updatetotal();
			}
        
		
	}
    /*console.log(orderedlist);*/
}

function totalvalue(value,id)
{
    /*console.log(orderedlist);*/
    
    
	for(let i=0;i<orderedlist.length;i++)
	{
        
		if(orderedlist[i][0]==id)
			{
                
                    orderedlist[i][2]=Number(value);
                    updatetotal();
			}
        
	}
    /*console.log(orderedlist);*/
}


function updatetotal()
{
    var total=0;
	for(let i=0;i<orderedlist.length;i++)
        {
            total=total+orderedlist[i][2];
        }
        document.getElementById("totalpromo").value=total;
}

function orderplaced(id)
{
	const od=window.location.assign('order/'+orderedlist);
	od.reload;
}


function submitorder()
{
	const form=document.getElementById('mylist');
	
	const flatarray=orderedlist.flat();
	const rows=orderedlist.length;
	const cols=orderedlist[0]?.length||0;
	
	flatarray.forEach((value,index)=>
		{
			const input=document.createElement('input');
					input.type='hidden';
					input.name=`orders[${index}]`;
					input.value=value;
					form.appendChild(input);
		});
	
		const rowinput=document.createElement('input');
				rowinput.type='hidden';
				rowinput.name='rows';
				rowinput.value=rows;
				form.appendChild(rowinput);
				
		const colsinput=document.createElement('input');
				colsinput.type='hidden';
			    colsinput.name='cols';
				colsinput.value=cols;
				form.appendChild(colsinput);
	
	
		form.submit();
}
