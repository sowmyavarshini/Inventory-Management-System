import React, {useState} from 'react'
import { listProducts } from '../services/ProductService'

const ListAllProducts = () => {

    const [products, setProducts]=useState([]);
    const [showProducts, setShowProducts] = useState(false); 

    const handleButtonClick = () => {
        listProducts()
          .then((response) => {
            setProducts(response.data);
            setShowProducts(true);
          })
          .catch((error) => {
            console.error(error);
          });
      };

   
  return (
    <div className="container">
      <div className="text-center">
        <button className="fetch-submit-btn mb-4" onClick={handleButtonClick}>
          List All Products
        </button>
      </div>

      {showProducts && (
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Stock</th>
                    <th>Price</th>
                    <th>Barcode</th>
                    <th>Brand</th>
                    <th>Category</th>
                  
                </tr>
            </thead>
            <tbody>
                {
                    products.map(product=>
                        <tr key={product.productId}>
                       
                            <td>{product.productId}</td>
                            <td>{product.productName}</td>
                            <td>{product.stockAvailable}</td>
                            <td>{product.price}</td>
                            <td>{product.barcode}</td>
                            <td>{product.brandName}</td>
                            <td>{product.categoryName}</td>
                        
                        </tr>
                    )
                }
           
            </tbody>
        </table>

)}
    </div>
  )
}

export default ListAllProducts