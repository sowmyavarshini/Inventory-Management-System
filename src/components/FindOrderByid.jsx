import React, { useState } from 'react';
import {fetchOrderById} from '../services/OrderService'
import '../App.css';

const FetchOrder = () => {
  const [orderId, setOrderId] = useState('');
  const [orderDetails, setOrderDetails] = useState(null);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const fetchOrder = async () => {
    if (orderId.trim() === '') {
      setError('Order ID cannot be blank.');
      return;
    }
    try {
     
      const response = await fetchOrderById(orderId);
      setOrderDetails(response.data);
      setMessage(`Order fetched successfully with ID: ${orderId}`);
      setError(''); 
    } catch (error) {
      console.error('Error fetching order:', error);
      setOrderDetails('');
      setMessage('Order not found.');
    }
  };

  return (
    <div className="fetch-container">
      <div className="card">
        <h3 className="text-center mt-2">Fetch Order by ID</h3>
        <div className="fetch-card-body">
          <div className="fetch-form-group">
            <label className="fetch-form-label">Enter Order ID</label>
            <input
              type="number"
              className="fetch-form-control"
              value={orderId}
              onChange={(e) => setOrderId(e.target.value)}
              min="1"
              placeholder="Order ID"
            />
             {error && <p className="text-danger mt-2">{error}</p>}
          </div>
         
          <div className="fetch-submit-btn-container">
            <button className="fetch-submit-btn" onClick={fetchOrder}>
              Fetch Order
            </button>
          </div>
          {message && <p className="fetch-text-success">{message}</p>}
          {orderDetails && (
            <div className="fetch-details">
           <h4 className='text-center'>Order Details</h4>
                <p><strong>Order ID:</strong> {orderDetails.orderId}</p>
                <p><strong>Ordered Quantity:</strong> {orderDetails.orderedQuantity}</p>
                <p><strong>Ordered Date:</strong> {orderDetails.orderedDate}</p>
                <p><strong>Delievery Date:</strong> {orderDetails.deliveryDate}</p>
                <p><strong>Product ID:</strong> {orderDetails.productId}</p>
                <p><strong>Product Name:</strong> {orderDetails.productName}</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default FetchOrder;
