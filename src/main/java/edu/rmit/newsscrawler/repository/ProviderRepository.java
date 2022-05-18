package edu.rmit.newsscrawler.repository;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ProviderRepository {

    private static ProviderRepository INSTANCE;

    public static final ProviderRepository getInstance() {
        if (ProviderRepository.INSTANCE == null) {
            INSTANCE = new ProviderRepository();
        }

        return ProviderRepository.INSTANCE;
    }

    private Map<String, Repository> providers;

    private ProviderRepository() {
        this.providers = Map.of(
                "VNEXPRESS", new VnExpressRepository(),
                "TUOITRE", new TuoiTreRepository(),
                "NHANDAN", new NhanDanRepository(),
                "ZING", new ZingNewsRepository(),
                "THANHNIEN", new ThanhNienRepository()
        );
    }


    public Repository getProvider(String provider) {
        return providers.get(provider);
    }

    public List<String> getProviders() {
        return new ArrayList<>(providers.keySet());
    }

}
