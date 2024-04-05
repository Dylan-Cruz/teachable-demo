<script setup lang="ts">
import type { EnrollmentReport } from '@/types/interfaces';
import EnrollmentReportCourse from './EnrollmentReportCourse.vue';
import { onMounted, reactive, ref } from 'vue';
import localClient from '../api/LocalClient';

const enrollmentReport = ref<EnrollmentReport>({
    courses: []
});

onMounted(async () => {
    const report = await localClient.getEnrollmentReport();
    enrollmentReport.value = report;
});
</script>

<template>
    <div>
        <h1>Enrollment Report</h1>
        <div v-for="course in enrollmentReport.courses" :key="course.id">
            <EnrollmentReportCourse :course="course" />
        </div>
    </div>
</template>

<style scoped>
h1 {
    color: #333;
    font-size: 24px;
    margin-bottom: 20px;

}
</style>