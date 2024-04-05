<script setup lang="ts">
import type { Course } from '../types/interfaces';
defineProps<{
    course: Course
}>()

</script>

<template>
    <div>
        <h2>{{ course.name }}</h2>
        <h3>{{ course.heading }}</h3>
        <h4>Enrolled Students:</h4>
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in course.enrolledStudents" :key="user.id">
                    <td>{{ user.name }}</td>
                    <td>{{ user.email }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<style scoped></style>