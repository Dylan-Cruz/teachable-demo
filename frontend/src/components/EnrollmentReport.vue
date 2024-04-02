<script setup lang="ts">
import type { Course } from '@/types/interfaces';
import EnrollmentReportCourse from './EnrollmentReportCourse.vue';
import { ref } from 'vue';

const courses = ref<Course[]>(
    [
        {
            id: 1,
            name: 'Vue 3',
            heading: 'Learn Vue 3',
            enrolledStudents: [
                {
                    id: 1,
                    name: 'John Doe',
                    email: 'john.doe@hotmail.com'
                }
            ]
        }
    ]
);
</script>

<template>
    <div>
        <h1>Enrollment Report</h1>
        <div v-for="course in courses" :key="course.name">
            <EnrollmentReportCourse :course="course" />
        </div>
    </div>
</template>