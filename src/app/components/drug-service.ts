import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DrugService {
  
  drug=new Drug();

  https=inject(HttpClient);

  addDrug(drug:any)
  {
    return this.https.post('http://localhost:8000/drug/adddrug',drug,{
      responseType:'text'
    })
  }

  updateDrug(drug:any)
  {
    return this.https.put('http://localhost:8000/drug/update',drug,{
      responseType:'text'
    });
  }

  deleteDrug(id:number)
  {
    return this.https.delete('http://localhost:8000/drug/'+id,{
      responseType:'text'
    });
  } 

  getAllDrugs()
  {
    return this.https.get('http://localhost:8000/drug/alldrugs');
  }

  getDrugById(id:number)
  {
    return this.https.get('http://localhost:8000/drug/'+id);
  }

  getCartProducts(id:number)
  {
    return this.https.get("http://localhost:8000/cart/"+id);
  }

  deleteCartProduct(cartId:number)
  {
    return this.https.delete("http://localhost:8000/cart/"+cartId,{
      responseType:'text'
    });
  }
  addToCart(memberId:number,drugId:number)
  {
    return this.https.post("http://localhost:8000/cart/addToCart/"+memberId+"/"+drugId,{
      responseType:'text'
    });
  }

}

class Drug{
  id:number=0;
  name:String='';
  type:String='';
  price:number=0;
  quantity:number=0;
}

