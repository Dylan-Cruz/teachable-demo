import type { EnrollmentReport } from "@/types/interfaces";

class LocalClient {
    baseUrl: string;
    constructor() {
        this.baseUrl = "http://localhost:8080";
    }

    async getEnrollmentReport(): Promise<EnrollmentReport> {
        const url = `${this.baseUrl}/reports/enrollment`;
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`Failed to fetch data from ${url} - ${response.status}`);
        }

        return response.json() as Promise<EnrollmentReport>;
    }
}

const localClient = new LocalClient();
export default localClient;