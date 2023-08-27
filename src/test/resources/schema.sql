CREATE TABLE Address (
    id INT PRIMARY KEY IDENTITY,
    streetAddress VARCHAR(255),
    city VARCHAR(40),
    state VARCHAR(40),
    Country VARCHAR(40),
    zipCode VARCHAR(10)
);

CREATE TABLE Users (
    id INT PRIMARY KEY IDENTITY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    DateOfBirth DATE,
    email VARCHAR(255),
    contactNumber VARCHAR(20),
    EmergencyContactNumber VARCHAR(20),
    password NVARCHAR(255),
    role VARCHAR(50),
    imageGovernmentIDProof VARBINARY (max)
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
    numberOfBedroom INT,
    numberOfBathroom INT,
    propertyType VARCHAR(255),
    checkinTime TIME,
    checkoutTime TIME,
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
    checkinTime DATETIME,
    checkoutTime DATETIME,
    numberOfGuest INT,
    experience_id INT,
);

ALTER TABLE Booking ADD CONSTRAINT FK_Booking_User FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Rooms ADD CONSTRAINT FK_Rooms_Address FOREIGN KEY (address_id) REFERENCES dbo.Address(id);
ALTER TABLE ExperienceImage ADD CONSTRAINT FK_ExperienceImage_Experience FOREIGN KEY (experience_id) REFERENCES Experience(id);
ALTER TABLE RoomImage ADD CONSTRAINT FK_RoomImage_Room FOREIGN KEY (room_id) REFERENCES Rooms(id);