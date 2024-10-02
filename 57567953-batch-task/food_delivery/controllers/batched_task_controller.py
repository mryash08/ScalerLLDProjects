from logging import getLogger, DEBUG

from food_delivery.dtos import BuildBatchedTaskRouteRequestDto
from food_delivery.services import BatchedTaskService


class BatchedTaskController:
    batched_task_service: BatchedTaskService

    def __init__(
            self,
            batched_task_service: BatchedTaskService
    ):
        self.batched_task_service = batched_task_service
        self.logger = getLogger(__name__)
        self.logger.setLevel(DEBUG)

    def build_route(
            self,
            request_dto: BuildBatchedTaskRouteRequestDto
    ):
        pass
