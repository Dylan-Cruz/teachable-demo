<script setup lang="ts">
import { ref } from 'vue';
import type { Course } from '../types/interfaces';
import EnrollmentReportStudent from './EnrollmentReportStudent.vue';
defineProps<{
    course: Course
}>()

</script>

<template>
    <div>
        <h2>{{ course.name }}</h2>
        <h3>{{ course.heading }}</h3>
        <div v-for="user in course.enrolledStudents" :key="user.id">
            <EnrollmentReportStudent :student="user" />
        </div>
    </div>
</template>