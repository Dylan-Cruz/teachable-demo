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
        <table>
            <tr>
                <th>Name</th>
                <th>Email</th>
            </tr>
            <tr v-for="user in course.enrolledStudents" :key="user.id">
                <td>{{ user.name }}</td>
                <td>{{ user.email }}</td>
            </tr>
        </table>
    </div>
</template>