import React, { useState } from 'react';
import { fetchProductsByCategory } from '../services/ProductService';
import '../App.css';

const FindProductByCategory = () => {
  const [categoryName, setCategoryName] = useState('');
  const [products, setProducts] = useState([]);
  const [message, setMessage] = useState('');
  const [errors, setErrors] = useState('');

  const handleFetchProducts = async () => {
    if (!categoryName) {
      setErrors('Category name cannot be blank.');
      return;
    }
    try {
      const response = await fetchProductsByCategory(categoryName);
      setProducts(response.data);
      setMessage(`Products fetched successfully for category: ${categoryName}`);
      setErrors('');
    } catch (error) {
      console.error('Error fetching products:', error);
      setMessage('');
      setErrors('Failed to fetch products for the specified brand.');
      setProducts('');
    }
  };

  return (
    <div className="container">
      <div className='text-center'>
        <h2>Fetch Products by Category</h2>
        <label><h5>Enter the category name:</h5></label>
        <input
          type="text"
          value={categoryName}
          className='form-control'
          onChange={(e) => setCategoryName(e.target.value)}
          style={{ width: '50%', margin: 'auto' }}
        />
        <button className='submit-btn mt-3 mb-3' onClick={handleFetchProducts}>
          Submit
        </button>
        {errors && <p className="text-danger">{errors}</p>}
      </div>

      {message && <p className="text-success">{message}</p>}

      {products.length > 0 && (
        <table className="table table-bordered mt-3  table-striped">
          <thead>
            <tr>
              <th>Product ID</th>
              <th>Product Name</th>
              <th>Stock Available</th>
              <th>Price</th>
              <th>Barcode</th>
              <th>Category</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr key={product.productId}>
                <td>{product.productId}</td>
                <td>{product.productName}</td>
                <td>{product.stockAvailable}</td>
                <td>{product.price}</td>
                <td>{product.barcode}</td>
                <td>{product.categoryName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default FindProductByCategory;
