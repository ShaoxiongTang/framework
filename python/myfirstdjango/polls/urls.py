from django.conf.urls import url
from django.contrib import admin

from polls.views import index

urlpatterns = [
    url(r'', index)
]
