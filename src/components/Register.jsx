import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify'; 
import { register, checkUserName, checkEmail, checkCityName, checkStateName, checkCountryName } from '../services/ProductService'
import '../App.css'; 

const Register = () => {
    const [userName, setUserName] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [email, setEmail] = useState('');
    const [cityName, setCityName] = useState('');
    const [stateName, setStateName] = useState('');
    const [countryName, setCountryName] = useState('');
    const [errors, setErrors] = useState({});
    const [userNameUnique, setUserNameUnique] = useState(true);
    const [emailUnique, setEmailUnique] = useState(true);
    const navigate = useNavigate();


    const validate = () => {
      const errors = {};
      const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{4,}$/;
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!userName) errors.userName = "User name cannot be blank.";
      if (!userPassword) {
        errors.userPassword = "User Password cannot be null.";
      }else if (!passwordRegex.test(userPassword)) {
        errors.userPassword = "Password must contain at least 4 characters, including 1 uppercase letter, 1 lowercase letter, 1 number, and 1 special character.";
    }
     
    if (!confirmPassword) {
      errors.confirmPassword = "Confirm Password cannot be null.";
  } else if (userPassword !== confirmPassword) {
      errors.confirmPassword = "Passwords do not match.";
  }

    
    
      if (!email) {
        errors.email = "Email cannot be blank.";
    } else if (!emailRegex.test(email)) {
        errors.email = "Invalid email format.";
    }
     
      if (!cityName) errors.cityName = "City name is mandatory.";
      if (!stateName) errors.stateName = "State name is mandatory.";
      if (!countryName) errors.countryName = "Country name is mandatory.";
      return errors;
    };

    const handleBlur = async (field) => {
      if (field === 'userName') {
        try {
          const response = await checkUserName(userName);
          setUserNameUnique(response.data);
          if (response.data) {
            setErrors(prevErrors => ({ ...prevErrors, userName: "User name already exists." }));
          } else {
            setErrors(prevErrors => ({ ...prevErrors, userName: "" }));
          }
        } catch (error) {
          console.error('Error checking user name:', error);
        }
      } else if (field === 'email') {
        try {
          const response = await checkEmail(email);
          setEmailUnique(response.data);
          if (response.data) {
            setErrors(prevErrors => ({ ...prevErrors, email: "Email already exists." }));
          } else {
            setErrors(prevErrors => ({ ...prevErrors, email: "" }));
          }
        } catch (error) {
          console.error('Error checking email:', error);
        }
      } else if (field === 'cityName') {
        try {
            const response = await checkCityName(cityName);
            if (!response.data) {
                setErrors(prevErrors => ({ ...prevErrors, cityName: "City not found." }));
            } else {
                setErrors(prevErrors => ({ ...prevErrors, cityName: "" }));
            }
        } catch (error) {
            console.error('Error checking city:', error);
        }
    } else if (field === 'stateName') {
        try {
            const response = await checkStateName(stateName);
            if (!response.data) {
                setErrors(prevErrors => ({ ...prevErrors, stateName: "State not found." }));
            } else {
                setErrors(prevErrors => ({ ...prevErrors, stateName: "" }));
            }
        } catch (error) {
            console.error('Error checking state:', error);
        }
    } else if (field === 'countryName') {
        try {
            const response = await checkCountryName(countryName);
            if (!response.data) {
                setErrors(prevErrors => ({ ...prevErrors, countryName: "Country not found." }));
            } else {
                setErrors(prevErrors => ({ ...prevErrors, countryName: "" }));
            }
          }catch (error) {
            console.error('Error checking country:', error);
        }
    }
        
    };
  
    const saveUser = async (e) => {
      e.preventDefault();
      const errors = validate();
      if (Object.keys(errors).length > 0) {
        setErrors(errors);
        return;
      }
  
      const user = { userName, userPassword, email, cityName, stateName, countryName};
      console.log('Request Payload:', user);
  
      try {
        const response = await register(user);
        console.log(response);
        toast.success('Registration successful');
        navigate('/'); 
        setErrors({});
      
  
      } catch (error) {
        console.error('Error:', error.message);
      }
    };

    return (
       <div style={{ "background-color": '#7EB4B6'}}>
      <div className='container'>
      <div className='row'>
        <div className='card'>
          <h2 className='text-center mt-2'>Register User</h2>
          <div className='card-body'>
            <form>
            <div className='row'>
            <div className='form-group mb-2'>
            <label className='form-label'>Enter the user name</label>
            <input
                type="text"
                value={userName}
                onChange={(e) => setUserName(e.target.value)}
                placeholder="Username"
                className={`form-control ${errors.userName ? 'is-invalid' : ''}`}
            
            onBlur={() => handleBlur('userName')}></input>
            {errors.userName && <p className="text-danger">{errors.userName}</p>}
            </div>
            <div className='form-group mb-2'>
            <label className='form-label'>Enter the password</label>
            <input
                type="password"
                value={userPassword}
                onChange={(e) => setUserPassword(e.target.value)}
                placeholder="Password"
                className={`form-control ${errors.userPassword ? 'is-invalid' : ''}`}
            />
            {errors.userPassword && <p className="text-danger">{errors.userPassword}</p>}
            </div>
            <div className='form-group mb-2'>
            <label className='form-label'>Confirm Password</label>
            <input
                type="password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                placeholder="Confirm Password"
                className='form-control'
            />
            {errors.confirmPassword && <p className="text-danger">{errors.confirmPassword}</p>}
            </div>
            <div className='form-group mb-2'>
            <label className='form-label'>Enter email</label>
            <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
                className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                onBlur={() => handleBlur('email')}
            />
            {errors.email && <p className="text-danger">{errors.email}</p>}
            </div>
            <div className='form-group mb-2'>
            <label className='form-label'>Enter City</label>
            <input
                type="text"
                value={cityName}
                onChange={(e) => setCityName(e.target.value)}
                placeholder="City"
                className={`form-control ${errors.cityName ? 'is-invalid' : ''}`}
                onBlur={() => handleBlur('cityName')}
            />
            {errors.cityName && <p className="text-danger">{errors.cityName}</p>}
            </div>
            <div className='form-group mb-2'>
            <label className='form-label'>Enter State</label>
            <input
                type="text"
                value={stateName}
                onChange={(e) => setStateName(e.target.value)}
                placeholder="State"
                className={`form-control ${errors.stateName ? 'is-invalid' : ''}`}
                onBlur={() => handleBlur('stateName')}
            />
            {errors.stateName && <p className="text-danger">{errors.stateName}</p>}
            </div>
            <div className='form-group mb-2'>
            <label className='form-label'>Enter Country</label>
            <input
                type="text"
                value={countryName}
                onChange={(e) => setCountryName(e.target.value)}
                placeholder="Country"
                className={`form-control ${errors.countryName ? 'is-invalid' : ''}`}
                onBlur={() => handleBlur('countryName')}
            />
            {errors.countryName && <p className="text-danger">{errors.countryName}</p>}
            </div>
            <div className="submit-btn-container">
              <button className='submit-btn' onClick={saveUser}>Register</button>
              </div>
              <p className='text-center'>Already have an account? <a href="/login">Login</a></p>
              </div>
             
              
            </form>
          </div>
        </div>
      </div>
    </div>
    </div>
           
       
    );
};

export default Register;





