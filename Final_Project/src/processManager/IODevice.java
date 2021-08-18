package processManager;

public class IODevice {
	public enum EIODeviceState {
		eIdle, eRunning, eTerminated, eError
	}

	private String deviceName;
	private EIODeviceState eIODeviceState;

	public IODevice() {
		this.eIODeviceState = EIODeviceState.eIdle;
	}
}
