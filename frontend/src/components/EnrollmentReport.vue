<script setup lang="ts">
import type { EnrollmentReport } from '@/types/interfaces';
import EnrollmentReportCourse from './EnrollmentReportCourse.vue';
import { onMounted, reactive, ref } from 'vue';
import localClient from '../api/LocalClient';

const enrollmentReport = ref<EnrollmentReport>({
    courses: []
});

onMounted(async () => {

    // const report = await localClient.getEnrollmentReport();
    const report = { "courses": [{ "id": 2002434, "name": "Flower Buddies", "heading": "Unlock the Joy of Gardening with Flower Buddies", "enrolledStudents": [] }, { "id": 2002435, "name": "Growing Delicious Heirloom Tomatoes", "heading": "Grow your own flavorful heirlooms with our easy-to-follow online course", "enrolledStudents": [{ "id": 89126037, "name": "Phoebe Terese", "email": "phoebeterese@test.com" }, { "id": 89125159, "name": "Mr. Frog", "email": "mrfrog@test.com" }, { "id": 89125315, "name": "Tim Wright", "email": "timwright@test.com" }] }, { "id": 2002436, "name": "Herbal Teas & Other Garden Tinctures", "heading": "Brew your own natural remedies with Herbal Teas &amp; Other Garden Tinctures", "enrolledStudents": [{ "id": 89125156, "name": "Valerie Frizzle", "email": "valeriefrizzle@test.com" }, { "id": 88958558, "name": "Ms. Frizzle", "email": "kristin.moser@teachable.com" }, { "id": 104683905, "name": "dylan", "email": "dylan-cruz@live.com" }, { "id": 89125157, "name": "Wanda Li", "email": "wandali@test.com" }] }, { "id": 2002431, "name": "Mushroom Madness", "heading": "Unlock the Secrets of Mushroom Foraging with Mushroom Madness", "enrolledStudents": [{ "id": 88958558, "name": "Ms. Frizzle", "email": "kristin.moser@teachable.com" }, { "id": 89125316, "name": "Jyoti Kaur", "email": "jyotikaur@test.com" }, { "id": 89125159, "name": "Mr. Frog", "email": "mrfrog@test.com" }, { "id": 89125160, "name": "Arnold Perlstein", "email": "arnoldperlstein@test.com" }, { "id": 89125156, "name": "Valerie Frizzle", "email": "valeriefrizzle@test.com" }, { "id": 89125314, "name": "Carlos Ramón", "email": "carlosramon@test.com" }, { "id": 89125313, "name": "Keesha Franklin", "email": "keeshafranklin@test.com" }, { "id": 89125157, "name": "Wanda Li", "email": "wandali@test.com" }] }, { "id": 2002430, "name": "Trees and Arboreous Concerns", "heading": "Grow Your Knowledge of Trees and Arboreous Concerns - Learn From the Expert!", "enrolledStudents": [{ "id": 88958558, "name": "Ms. Frizzle", "email": "kristin.moser@teachable.com" }, { "id": 104683905, "name": "dylan", "email": "dylan-cruz@live.com" }, { "id": 89125316, "name": "Jyoti Kaur", "email": "jyotikaur@test.com" }, { "id": 89125158, "name": "Mr. Toad", "email": "mrtoad@test.com" }, { "id": 89125159, "name": "Mr. Frog", "email": "mrfrog@test.com" }, { "id": 89125160, "name": "Arnold Perlstein", "email": "arnoldperlstein@test.com" }, { "id": 89125313, "name": "Keesha Franklin", "email": "keeshafranklin@test.com" }, { "id": 89125314, "name": "Carlos Ramón", "email": "carlosramon@test.com" }, { "id": 89125315, "name": "Tim Wright", "email": "timwright@test.com" }, { "id": 89125156, "name": "Valerie Frizzle", "email": "valeriefrizzle@test.com" }, { "id": 89125157, "name": "Wanda Li", "email": "wandali@test.com" }] }] }
    enrollmentReport.value = report;
});
</script>

<template>
    <div class="mb-5">
        <h1 class="m-5">Enrollment Report</h1>
        <div v-for="course in enrollmentReport.courses" :key="course.id">
            <EnrollmentReportCourse :course="course" />
        </div>
    </div>
</template>

<style scoped>
h1 {
    text-align: center;
}
</style>