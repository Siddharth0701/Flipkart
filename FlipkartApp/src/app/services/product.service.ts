import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Cart } from '../interfaces/cart';
import { Order } from '../interfaces/order';
import { Product } from '../interfaces/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
cartData=new EventEmitter<Product[] |[]>();
  constructor(private http:HttpClient) { }

  addProduct(data: Product) {
    return this.http.post('http://localhost:8080/api/products', data);
  }
  productList() {
    return this.http.get<Product[]>('http://localhost:8080/api/products');
  }

  deleteProduct(id: number) {
    return this.http.delete(`http://localhost:8080/api/products/${id}`);
  }

  getProduct(id: string) {
    return this.http.get<Product>(`http://localhost:8080/api/products/${id}`);
  }

  updateProduct(product: Product) {
    return this.http.put<Product>(
      `http://localhost:8080/api/products/${product.id}`,
      product
    );
  }
  popularProducts() {
    return this.http.get<Product[]>('http://localhost:8080/api/products?_limit=3');
  }

  trendyProducts() {
    return this.http.get<Product[]>('http://localhost:8080/api/products?_limit=8');
  }

  searchProduct(query: string) {
    return this.http.get<Product[]>(
      `http://localhost:8080/api/products?q=${query}`
    );
  }

  localAddToCart(data: Product) {
    let cartData = [];
    let localCart = localStorage.getItem('localCart');
    if (!localCart) {
      localStorage.setItem('localCart', JSON.stringify([data]));
      this.cartData.emit([data]);
    } else {
      cartData = JSON.parse(localCart);
      cartData.push(data);
      localStorage.setItem('localCart', JSON.stringify(cartData));
      this.cartData.emit(cartData);
    }
  }

  removeItemFromCart(productId: number) {
    let cartData = localStorage.getItem('localCart');
    if (cartData) {
      let items: Product[] = JSON.parse(cartData);
      items = items.filter((item: Product) => productId !== item.id);
      localStorage.setItem('localCart', JSON.stringify(items));
      this.cartData.emit(items);
    }
  }

  addToCart(cartData: Cart) {
    return this.http.post('http://localhost:8080/api/cart', cartData);
  }
  getCartList(userId: number) {
    return this.http
      .get<Product[]>('http://localhost:8080/api/cart?userId=' + userId, {
        observe: 'response',
      })
      .subscribe((result) => {
        if (result && result.body) {
          this.cartData.emit(result.body);
        }
      });
  }
  removeToCart(cartId: number) {
    return this.http.delete('http://localhost:3000/cart/' + cartId);
  }
  currentCart() {
    let userStore = localStorage.getItem('user');
    let userData = userStore && JSON.parse(userStore);
    return this.http.get<Cart[]>('http://localhost:8080/api/cart?userId=' + userData.id);
  }

  orderNow(data: Order) {
    return this.http.post('http://localhost:8080/api/order', data);
  }
  orderList() {
    let userStore = localStorage.getItem('user');
    let userData = userStore && JSON.parse(userStore);
    return this.http.get<Order[]>('http://localhost:8080/api/orders?userId=' + userData.id);
  }

  deleteCartItems(cartId: number) {
    return this.http.delete('http://localhost:8080/api/cart/' + cartId).subscribe((result) => {
      this.cartData.emit([]);
    })
  }

  cancelOrder(orderId:number){
    return this.http.delete('http://localhost:8080/api/orders/'+orderId)

  }








}