import React, {useState} from 'react'
import { listOrders } from '../services/OrderService'

const ListAllOrders = () => {
    const [orders, setOrders]=useState([])
    const [showOrders, setShowOrders] = useState(false);

    const handleButtonClick = () => {
        listOrders()
          .then((response) => {
            setOrders(response.data);
            setShowOrders(true);
          })
          .catch((error) => {
            console.error(error);
          });
      };

   
  return (
    <div className="container">
    <div className="text-center">
      <button className="fetch-submit-btn mb-4" onClick={handleButtonClick}>
        List All Orders
      </button>
    </div>

    
    {showOrders && (
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>OrderedQuantity</th>
                    <th>OrderedDate</th>
                    <th>DeliveryDate</th>
                    <th>Product</th>
                    
                </tr>
            </thead>
            <tbody>
                {
                    orders.map(order=>
                        <tr key={order.orderId}>
                       
                            <td>{order.orderId}</td>
                            <td>{order.orderedQuantity}</td>
                            <td>{order.orderedDate}</td>
                            <td>{order.deliveryDate}</td>
                            <td>{order.productName}</td>
                        
                          
                        </tr>
                    )
                }
           
            </tbody>
        </table>
    )}

        </div>
      
  )
}

export default ListAllOrders