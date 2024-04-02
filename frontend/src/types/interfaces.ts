export interface EnrollmentReport {
    courses: Course[];
}

export interface Course {
    id: number;
    name: string;
    heading: string;
    enrolledStudents: Student[];
}

export interface Student {
    id: number;
    name: string;
    email: string;
}