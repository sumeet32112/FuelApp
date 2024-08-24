export interface Vehicle{
    vehicleClass : string;
    registrationNumber : string;
    owner : string;
    brand : string;
    model : string;
    fuelType : string;
    monthManufactured : string;
    yearManufactured : Number;
    dateOfPurchase : Date;
    engineCapacity : Number;
    colour : string;
}

export interface ColumnElements {
    registrationNumber:string,
    vehicleOwner:string,
    vehicleBrand:string,
    fuelType:string,
    monthManufactured:string,
    yearManufactured:number,
    engineCapacity:number,
    colour:string  
}