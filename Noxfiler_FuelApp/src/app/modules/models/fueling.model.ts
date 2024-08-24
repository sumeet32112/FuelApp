export interface Fueling{
  vehicle: string;
  fuelDensity: number;
  pricePerLitre: number;
  filledQuantity: number;
  distanceCovered: number;
  vehicleMileage: number;
  timeTravelled: string;
  allowedDelta: number;
  comments: string;
  agency: string;
  dateOfFueling: Date;
}

export interface PeriodicElement {
  date : Date,
  registrationNumber:string,
  agency:string,
  fuelType:string,
  density:number,
  engineEconomyUsage:number,
  delta:number,
  allowedDelta:number
  }