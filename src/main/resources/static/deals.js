document.addEventListener('DOMContentLoaded',function()
{
	
	if(typeof drugslist!=='undefined' && Array.isArray(drugslist))
	{
		const typelist=[];
		typelist[0]=drugslist[0].type;
		let k=1;
		for(let i=1;i<drugslist.length-1;i++)
		{
			if(drugslist[i].type!=drugslist[i+1].type);
			{
				typelist.push(drugslist[i+1].type);
			}
		}
		for(let i=0;i<type.length;i++)
		{
			var divtag=document.createElement("div");
			document.body.appendChild(divtag);
			divtag.style.height="63vh";
			divtag.style.width="95vw";
			divtag.style.marginTop="2%";
			divtag.style.borderRadius="10px";
			divtag.style.marginLeft="3%";
			// divtag.style.backgroundColor="blue";
	
			var h2tag=document.createElement("h2");
			divtag.appendChild(h2tag);
			h2tag.innerHTML="Today's Deals";
			h2tag.style.color="black";
			h2tag.style.marginLeft="30px";
			h2tag.style.fontSize="230%";
			h2tag.style.marginTop="15px";
			h2tag.style.marginBottom="1%";

			var productstag=document.createElement("div");
			divtag.appendChild(productstag);
			productstag.style.height="78%";
			productstag.style.width="95%";
			// productstag.style.marginTop="1%";
			productstag.style.borderRadius="10px";
			productstag.style.marginLeft="3%";
			productstag.style.marginBottom="0.5%";
			// productstag.style.backgroundColor="red";
			productstag.style.float="left";
	
			var viewmoretag=document.createElement("input");
			divtag.appendChild(viewmoretag);
			viewmoretag.id="view";
			viewmoretag.type="button";
			viewmoretag.value="View More";
			viewmoretag.style.float="left";
			viewmoretag.style.marginLeft="87%";
			viewmoretag.style.height="4.5vh";
			viewmoretag.style.width="8%";
			viewmoretag.style.fontWeight="bold";
			viewmoretag.style.fontSize="90%";

			for(let j=0;j<4;j++)
			{
				var p1tag=document.createElement("div");
				productstag.appendChild(p1tag);
				p1tag.style.height="89%";
				p1tag.style.width="20%";
				p1tag.style.marginTop="1%";
				p1tag.style.borderRadius="10px";
				p1tag.style.marginLeft="4%";
				// productstag.style.marginBottom="3%";
				p1tag.style.border="2px solid green";
				p1tag.style.boxShadow="5px 5px 8px grey";
				p1tag.style.backgroundColor="white";
				p1tag.style.float="left";
		

				var imgtag=document.createElement("img");
				p1tag.appendChild(imgtag);
				imgtag.src="./lp-1.jpg";
				imgtag.style.height="64%";
				imgtag.style.width="93%";
				imgtag.style.marginLeft="3.5%";
				imgtag.style.marginTop="3.5%";
				imgtag.style.marginBottom="0px"
				var h31tag=document.createElement("h3");
				p1tag.appendChild(h31tag);
				h31tag.innerHTML="ABC";
				h31tag.style.marginTop="0px";
				var h32tag=document.createElement("h3");
				p1tag.appendChild(h32tag);
				h32tag.style.marginTop="0px";
				h32tag.innerHTML="Price:150/-";
				h31tag.style.marginBottom="0%";
				// h31tag.style.float="left";
				h31tag.style.fontSize="20px";
				h31tag.style.textAlign="center";
				h32tag.style.fontSize="20px";
				h32tag.style.textAlign="center";
	
				var actag=document.createElement("input");
				p1tag.appendChild(actag);
				actag.id="AC";
				actag.type="button";
				actag.value="Add to cart";
				actag.style.height="5vh";
				actag.style.width="43%";
				// actag.style.marginTop="3%";
				actag.style.borderRadius="5px";
				actag.style.marginLeft="8%";
				actag.style.marginBottom="4%";
				actag.style.fontWeight="bold";
				actag.style.fontSize="90%";
				actag.style.overflow="hidden";
				actag.style.marginTop="0px";
				// console.log(p1tag);


				var mdtag=document.createElement("input");
				p1tag.appendChild(mdtag);
				mdtag.id="MD";
				mdtag.type="button";
				mdtag.value="More Details";
				mdtag.style.height="5vh";
				actag.style.width="43%";
				// mdtag.style.marginTop="3%";
				mdtag.style.borderRadius="5px";
				mdtag.style.marginLeft="4%";
				mdtag.style.marginBottom="4%";
				mdtag.style.fontWeight="bold";
				mdtag.style.fontSize="90%";
				mdtag.style.overflow="hidden";
				mdtag.style.marginTop="0px";
				// console.log(p1tag);
			}

		}
	}

});
