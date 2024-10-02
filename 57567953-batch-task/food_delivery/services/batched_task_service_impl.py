from typing import List

from food_delivery.adapters import MapsAdapter
from food_delivery.models import Location
from .batched_task_service import BatchedTaskService


class BatchedTaskServiceImpl(BatchedTaskService):
    maps_adapter: MapsAdapter

    def __init__(self, maps_adapter: MapsAdapter):
        self.maps_adapter = maps_adapter

    def build_route(self, batched_task_id: int) -> List[Location]:
        pass
