package com.epicode.Spring.security.payload;
import java.time.LocalDateTime;
import com.epicode.Spring.security.entity.Hall;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewScheduleResponse {
	private Long id;
	private MovieResponse movie;
	private Hall hall;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
