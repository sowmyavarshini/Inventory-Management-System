import React, { useState } from 'react';
import {generateCustomerReport } from '../services/ProductService';
import '../App.css';

const CustomerReport = () => {
  const [customerId, setCustomerId] = useState('');
  const [userName, setUserName] = useState(''); 
  const [report, setReport] = useState([]);
  const [errors, setErrors] = useState('');

  const handleFetchReport = async () => {

    if (customerId.trim() === '') {
      setErrors('Customer ID cannot be blank.');
      return;
    }

    try {
      const response = await generateCustomerReport(customerId);
      setUserName(response.data[0].userName); 
      setReport(response.data);
      setErrors('');
    } catch (error) {
      console.error('Error fetching customer report:', error);
      setErrors('Failed to fetch customer report.');
      setUserName('');
      setReport([]);
    }
  };

  return (
    <div className="container">
      <h2 className='text-center'>Customer Report</h2>

      {/* Step 1: Enter Customer ID */}
      <div className='text-center'>
        <label><h5>Enter Customer ID:</h5></label>
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
          <input
            type="number"
            value={customerId}
            className='form-control'
            onChange={(e) => setCustomerId(e.target.value)}
            min="1"
            style={{ width: '25%' }}
          />
        </div>
        <button className='submit-btn mt-3' onClick={handleFetchReport}>Fetch Report</button>
        {errors && <p className="text-danger mt-2">{errors}</p>}
      </div>

      {/* Step 2: Display Report */}
      {report.length > 0 && (
        <div className='mt-4'>
          <h5 className='text-center'>Report for Customer ID: {customerId}</h5>
          {userName && (
        <div className="mt-3">
          <h5>UserName: {userName}</h5>
        </div>
      )}
          <table className='table table-bordered mt-3 table-striped'>
            <thead>
              <tr>
              
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Ordered Quantity</th>
                <th>Amount Paid</th>
              </tr>
            </thead>
            <tbody>
              {report.map((item) => (
                <tr key={item.productId}>
          
                  <td>{item.productId}</td>
                  <td>{item.productName}</td>
                  <td>{item.price}</td>
                  <td>{item.orderedQuantity}</td>
                  <td>{item.amountPaid}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default CustomerReport;
