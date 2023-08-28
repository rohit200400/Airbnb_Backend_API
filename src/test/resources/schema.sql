CREATE TABLE Address (
    id INT PRIMARY KEY IDENTITY,
    street_address VARCHAR(255),
    city VARCHAR(40),
    state VARCHAR(40),
    country VARCHAR(40),
    zipCode VARCHAR(10)
);

CREATE TABLE Users (
    id INT PRIMARY KEY IDENTITY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    email VARCHAR(255) NOT NULL UNIQUE,
    contact_number VARCHAR(20),
    emergency_contact_number VARCHAR(20),
    password NVARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE User_ID_Proof
  (user_id INT NOT NULL,
  image_governmentidproof VARBINARY (max)
  CONSTRAINT FK_User_Idproof FOREIGN KEY (user_id) REFERENCES Users(id)
  );

CREATE TABLE RoomImage (
    id INT PRIMARY KEY IDENTITY,
    name VARCHAR(255),
    description VARCHAR(1000),
    image VARCHAR(MAX),
    room_id INT
);


CREATE TABLE ExperienceImage (
    id INT PRIMARY KEY IDENTITY,
    name VARCHAR(255),
    description VARCHAR(1000),
    image VARCHAR(MAX),
    experience_id INT,
);

CREATE TABLE Experience (
    id INT PRIMARY KEY IDENTITY,
    rating INT,
    feedback VARCHAR(1000)
);


CREATE TABLE Amenities (
    id INT PRIMARY KEY IDENTITY,
    name VARCHAR(50),
    description VARCHAR(100)
);

CREATE TABLE Rooms (
    id INT PRIMARY KEY IDENTITY,
    address_id INT,
    rate FLOAT,
    number_of_bedroom INT,
    number_of_bathroom INT,
    property_type VARCHAR(255),
    checkin_time TIME,
    checkout_time TIME,
    capacity INT,
);


CREATE TABLE RoomAmenities (
    room_id INT,
    amenities_id INT,
    PRIMARY KEY (room_id, amenities_id),
    CONSTRAINT FK_RoomAmenities_Room FOREIGN KEY (room_id) REFERENCES Rooms(id),
    CONSTRAINT FK_RoomAmenities_Amenities FOREIGN KEY (amenities_id) REFERENCES Amenities(id)
);


CREATE TABLE Booking (
    id INT PRIMARY KEY IDENTITY,
    user_id INT,
    checkin_time DATETIME,
    checkout_time DATETIME,
    number_of_guest INT,
    experience_id INT,
);

ALTER TABLE Booking ADD CONSTRAINT FK_Booking_User FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Rooms ADD CONSTRAINT FK_Rooms_Address FOREIGN KEY (address_id) REFERENCES dbo.Address(id);
ALTER TABLE ExperienceImage ADD CONSTRAINT FK_ExperienceImage_Experience FOREIGN KEY (experience_id) REFERENCES Experience(id);
ALTER TABLE RoomImage ADD CONSTRAINT FK_RoomImage_Room FOREIGN KEY (room_id) REFERENCES Rooms(id);