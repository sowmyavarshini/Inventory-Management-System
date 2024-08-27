import React, { useState } from 'react';
import {fetchProductById} from '../services/ProductService'
import '../App.css'; 

const FetchProduct = () => {
  const [productId, setProductId] = useState('');
  const [productDetails, setProductDetails] = useState(null);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const fetchProduct = async () => {
    if (productId.trim() === '') {
      setError('Product ID cannot be blank.');
      return;
    }
    try {
   
      const response = await fetchProductById(productId);
      setProductDetails(response.data);
      setMessage(`Product fetched successfully with ID: ${productId}`);
      setError(''); 
    } catch (error) {
      console.error('Error fetching product:', error);
      setProductDetails('');
      setMessage('Product not found.');
    }
  };

  return (
    <div className="fetch-container">
      <div className="card">
        <h3 className="text-center mt-2">Fetch Product by ID</h3>
        <div className="fetch-card-body">
          <div className="fetch-form-group">
            <label className="fetch-form-label">Enter Product ID</label>
            <input
              type="number"
              className="fetch-form-control"
              value={productId}
              onChange={(e) => setProductId(e.target.value)}
              min="1"
              placeholder="Product ID"
            />
             {error && <p className="text-danger mt-2">{error}</p>}
          </div>
          <div className="fetch-submit-btn-container">
            <button className="fetch-submit-btn" onClick={fetchProduct}>
              Fetch Product
            </button>
          </div>
          {message && <p className="fetch-text-success">{message}</p>}
          {productDetails && (
            <div className="fetch-details">
           <h4 className='text-center'>Product Details</h4>
                <p><strong>Name:</strong> {productDetails.productName}</p>
                <p><strong>Stock Available:</strong> {productDetails.stockAvailable}</p>
                <p><strong>Price:</strong> ₹{productDetails.price}</p>
                <p><strong>Barcode:</strong> {productDetails.barcode}</p>
                <p><strong>Brand:</strong> {productDetails.brandName}</p>
                <p><strong>Category:</strong> {productDetails.categoryName}</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default FetchProduct;
