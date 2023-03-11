package self.learning.lib.utils;

public final class Views {

    private Views() {}

    public static interface RestGet {
    }

    public static interface RestCreate {
    }

    public static interface RestUpdate {
    }

    public static final class District {
        private District() {

        }

		public static interface Get extends RestGet {

		}

        public static interface GetDetails extends Get {

        }
    }

    public static final class Upazila {
        private Upazila() {

        }

		public static interface Get extends RestGet {

		}

        public static interface GetDetails extends Get {

        }
    }

    public static final class GeoCluster {
        private GeoCluster() {

        }

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

    public static final class School {
        private School() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }

    }

    public static final class MasterDataEntry {
        private MasterDataEntry() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

    public static final class ApprovalRequest {

        public interface Get extends RestGet {

        }

        public interface Post {

        }

    }

    public static final class PermissionCategory {
        public interface Get {

        }
        public interface Redis {

        }
    }

    public static final class Permission {
        public interface Get {

        }


        public static interface Redis {
        }
    }

    public static final class IEIMSUser {
        private IEIMSUser() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        // For /logged-in-user-details through API Gateway
        public static interface GetDetailsV3 extends Get {

        }

        public static interface Put {

        }
    }

    public static final class Complaint {
        private Complaint() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

    public static final class Teacher {
        private Teacher() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

    public static final class TeacherJobPosting {
        private TeacherJobPosting() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

    public static final class StudentSummary {
        private StudentSummary() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }

    }

    public static final class Census {
        private Census() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

	public static final class BookLot {
		private BookLot() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
	}

	public static final class BookLotReceive {
		private BookLotReceive() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
	}

    public static final class Training {
        private Training() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

    public static final class TrainingVenue {
        private TrainingVenue() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

    public static final class TrainingParticipantType {
        private TrainingParticipantType() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

    public static final class TrainerAssignment {
        private TrainerAssignment() {}

        public static interface Post {

        }

        public static interface Get extends RestGet {

        }

        public static interface GetDetails extends Get {

        }

        public static interface Put {

        }
    }

	public static final class TrainingParticipant {
		private TrainingParticipant() {}

		public static interface Post {

		}

		public static interface Get extends RestGet {

		}

		public static interface GetDetails extends Get {

		}

		public static interface Put {

		}
	}

	public static final class VenueGroupItem {
		private VenueGroupItem() {}

		public static interface Post {

		}

		public static interface Get extends RestGet {

		}

		public static interface GetDetails extends Get {

		}

		public static interface Put {

		}
	}

	public static final class TrainingVenueGroup {
		private TrainingVenueGroup() {}

		public static interface Post {

		}

		public static interface Get extends RestGet {

		}

		public static interface GetDetails extends Get {

		}

		public static interface Put {

		}
	}
}
