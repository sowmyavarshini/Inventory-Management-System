import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import carousel1 from '../assets/images/carousel1.webp';
import carousel2 from '../assets/images/carousel2.jpeg';
import carousel3 from '../assets/images/carousel3.jpeg';

const Home = () => {
  const carouselItems = [
    {
      src: carousel1,
      alt: 'First slide',
      heading: 'Welcome to Inventrak',
      slogan: 'Streamline Your Inventory, Elevate Your Business.',
    },
    {
      src: carousel2,
      alt: 'Second slide',
      heading: 'Manage Your Products',
      slogan: 'Efficiently track and manage your entire inventory.',
    },
    {
      src: carousel3,
      alt: 'Third slide',
      heading: 'Grow Your Business',
      slogan: 'Make informed decisions with real-time data.',
    },
  ];

  return (
    <div id="homeCarousel" className="carousel slide" data-bs-ride="carousel">
      <div className="carousel-inner">
        {carouselItems.map((item, index) => (
          <div key={index} className={`carousel-item ${index === 0 ? 'active' : ''}`}>
            {/* Heading and Slogan Above the Image */}
            <div className="carousel-caption-above text-center my-4">
              <h4>{item.heading}</h4>
              <p>{item.slogan}</p>
            </div>
            {/* Carousel Image */}
            <img
              src={item.src}
              className="d-block w-100"
              alt={item.alt}
              style={{ height: '500px', objectFit: 'cover' }} 
            />
          </div>
        ))}
      </div>
      <button className="carousel-control-prev" type="button" data-bs-target="#homeCarousel" data-bs-slide="prev">
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button className="carousel-control-next" type="button" data-bs-target="#homeCarousel" data-bs-slide="next">
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default Home;
