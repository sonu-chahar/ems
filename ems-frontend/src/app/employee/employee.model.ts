export interface IEmployee {
    id: number;
    name: string;
    phoneNumber:string
    emailId: string;

    departmentId?: number;
    userType?: string;
    
    dateOfJoining: string;
    dateOfBirth: string;
   
    reportingUserId?: number;
}

